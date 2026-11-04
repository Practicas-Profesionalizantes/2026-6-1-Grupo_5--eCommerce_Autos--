import React from "react";

function AuthLayout({ children, panelIzquierdo, panelDerecho }) {
  return (
    <div className="main-container">
      <div className="side-panel">{panelIzquierdo}</div>
      <div className="form-panel">{children}</div>
      <div className="side-panel">{panelDerecho}</div>
    </div>
  );
}

export default AuthLayout;