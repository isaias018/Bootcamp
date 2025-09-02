import React from 'react';
import './ButtonGallery.css';

const ButtonGallery = () => {
  return (
    <section className="section">
      <h3 className="component-title">Galería de botones</h3>
      <div className="button-grid">
        <button className="btn primary">LABEL</button>
        <button className="btn secondary">LABEL</button>
        <button className="btn outlined">LABEL</button>
        
        <button className="btn icon">
          <span className="btn-icon">+</span> LABEL
        </button>
        <button className="btn icon">
          <span className="btn-icon">+</span> LABEL
        </button>
        <button className="btn icon">
          <span className="btn-icon">+</span> LABEL
        </button>
        
        <button className="btn text">LABEL +</button>
        <button className="btn text">LABEL +</button>
        <button className="btn text">LABEL +</button>
        
        <button className="btn disabled" disabled>LABEL +</button>
        <button className="btn disabled" disabled>LABEL +</button>
        <button className="btn disabled" disabled>LABEL +</button>
      </div>
    </section>
  );
};

export default ButtonGallery;