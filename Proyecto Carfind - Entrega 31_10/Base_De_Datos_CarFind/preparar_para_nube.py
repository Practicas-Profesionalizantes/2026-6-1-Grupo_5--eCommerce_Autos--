"""
Genera compraventa_nube.sql a partir de compraventa.sql (exportado desde phpMyAdmin / XAMPP)
para poder importarlo en un MySQL administrado en la nube (Aiven, Railway, etc.).

Cambios que hace:
  1. Mueve cada PRIMARY KEY al CREATE TABLE. phpMyAdmin las agrega al final con ALTER TABLE,
     pero Aiven exige que toda tabla tenga clave primaria desde que se crea (sql_require_primary_key).
  2. Quita las tablas "Stand-in" de las vistas (son tablas temporales sin clave primaria que
     phpMyAdmin crea y después reemplaza por la vista real).
  3. Quita DEFINER=`root`@`localhost` de las vistas: en la nube no se tiene el usuario root
     con permisos de administrador, y crear una vista "a nombre de" otro usuario falla.

Uso (desde esta carpeta):  python preparar_para_nube.py
Volver a correrlo cada vez que se exporte de nuevo compraventa.sql.
"""
import re
from pathlib import Path

CARPETA = Path(__file__).resolve().parent
ORIGEN = CARPETA / "compraventa.sql"
DESTINO = CARPETA / "compraventa_nube.sql"


def mover_claves_primarias(sql: str) -> str:
    patron_alter = re.compile(
        r"ALTER TABLE `(?P<tabla>[^`]+)`\n  ADD PRIMARY KEY \((?P<columnas>[^)]*)\)(?P<fin>,\n  |;\n)"
    )
    claves = {}

    def quitar_del_alter(m):
        claves[m.group("tabla")] = m.group("columnas")
        if m.group("fin") == ";\n":
            return ""  # el ALTER solo agregaba la clave primaria
        return f"ALTER TABLE `{m.group('tabla')}`\n  "

    sql = patron_alter.sub(quitar_del_alter, sql)

    for tabla, columnas in claves.items():
        patron_create = re.compile(
            r"(CREATE TABLE `" + re.escape(tabla) + r"` \(\n.*?)\n(\) ENGINE=)", re.DOTALL
        )
        sql, cantidad = patron_create.subn(
            lambda m: f"{m.group(1)},\n  PRIMARY KEY ({columnas})\n{m.group(2)}", sql, count=1
        )
        if cantidad != 1:
            raise SystemExit(f"No encontré el CREATE TABLE de `{tabla}`")
    return sql


def quitar_stand_ins(sql: str) -> str:
    return re.sub(r"CREATE TABLE `vista_[^`]+` \(\n.*?\n\);\n", "", sql, flags=re.DOTALL)


def quitar_definer(sql: str) -> str:
    sql = re.sub(r"DEFINER=`[^`]+`@`[^`]+` ", "", sql)
    return sql.replace("SQL SECURITY DEFINER", "SQL SECURITY INVOKER")


def main():
    sql = ORIGEN.read_text(encoding="utf-8")
    sql = mover_claves_primarias(sql)
    sql = quitar_stand_ins(sql)
    sql = quitar_definer(sql)
    encabezado = (
        "-- GENERADO por preparar_para_nube.py a partir de compraventa.sql: no editar a mano.\n"
        "-- Versión para MySQL en la nube (Aiven, Railway, etc.). Ver CarFindWeb/README.md.\n\n"
    )
    DESTINO.write_text(encabezado + sql, encoding="utf-8", newline="\n")
    print(f"Listo: {DESTINO.name}")


if __name__ == "__main__":
    main()
