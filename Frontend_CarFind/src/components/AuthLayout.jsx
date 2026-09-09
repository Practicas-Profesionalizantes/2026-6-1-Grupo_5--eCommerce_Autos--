import React from "react";
import Header from "./Header";

function AuthLayout({ children, panelIzquierdo, panelDerecho }) {
  return (
    <div className="app-wrapper">
      <Header />
      <div className="main-container">
        <div className="side-panel">{panelIzquierdo}</div>
        <div className="form-panel">{children}</div>
        <div className="side-panel">{panelDerecho}</div>
      </div>
    </div>
  );
}

export default AuthLayout;