import React from 'react';
import './Footer.css';

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-content">
        <div className="footer-column">
          <h4 className="footer-title">Producto</h4>
          <a href="#icm" className="footer-link">ICM</a>
          <a href="#big" className="footer-link">BIG</a>
          <a href="#ons" className="footer-link">ONS</a>
        </div>
        
        <div className="footer-column">
          <h4 className="footer-title">Resources</h4>
          <a href="#gas" className="footer-link">Gas boiler</a>
          <a href="#rio" className="footer-link">RIO</a>
          <a href="#service" className="footer-link">Service</a>
          <a href="#smart" className="footer-link">Smart Wire</a>
          <a href="#delivery" className="footer-link">Delivery Gate</a>
        </div>
        
        <div className="footer-column">
          <h4 className="footer-title">Company</h4>
          <a href="#asset" className="footer-link">Asset</a>
          <a href="#oil" className="footer-link">Oil farm</a>
          <a href="#tax" className="footer-link">Tax-in-Customer</a>
          <a href="#mobile" className="footer-link">Mobile's Store</a>
          <a href="#phone" className="footer-link">Phone/Mob</a>
        </div>
        
        <div className="footer-column">
          <h4 className="footer-title">Contact Dr.</h4>
          <p className="footer-text">Información de contacto</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;