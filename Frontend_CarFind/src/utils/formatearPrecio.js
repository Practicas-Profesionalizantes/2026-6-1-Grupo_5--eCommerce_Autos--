const formatearPrecio = (valor) => {
    if (!valor || isNaN(valor)) return '$ 0';
    
    // Formatea números al estándar local (ej: 18500000 -> $ 18.500.000)
    return new Intl.NumberFormat('es-AR', {
        style: 'currency',
        currency: 'ARS',
        maximumFractionDigits: 0
    }).format(valor);
};

export default formatearPrecio;