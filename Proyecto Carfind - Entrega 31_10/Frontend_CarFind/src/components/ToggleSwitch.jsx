import React from 'react';

/**
 * Interruptor accesible (role="switch").
 * @param {{ checked: boolean, onChange: (valor: boolean) => void, label: string, disabled?: boolean }} props
 *   label: texto que leen los lectores de pantalla (el interruptor no tiene texto visible propio).
 */
function ToggleSwitch({ checked, onChange, label, disabled = false }) {
  return (
    <button
      type="button"
      role="switch"
      aria-checked={checked}
      aria-label={label}
      className={`toggle${checked ? ' toggle--on' : ''}`}
      disabled={disabled}
      onClick={() => onChange(!checked)}
    >
      <span className="toggle-knob" aria-hidden="true" />
    </button>
  );
}

export default ToggleSwitch;
