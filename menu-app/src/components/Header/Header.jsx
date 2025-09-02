import React from 'react';
import './Header.css';

const Header = () => {
  return (
    <header className="header">
      <div className="navbar">
        <div className="logo">
          <div className="logo-placeholder">Logo</div>
        </div>
        <nav className="nav">
          <a href="#producto" className="nav-link">Producto</a>
          <a href="#resources" className="nav-link">Resources</a>
          <a href="#company" className="nav-link">Company</a>
          <a href="#contact" className="nav-link">Contact Dr.</a>
        </nav>
      </div>
      
      <div className="banner">
        <h1 className="banner-title">Work less, achieve more.</h1>
        <p className="banner-text">
          Our technology is the key to unlocking higher sales and better service. 
          We stand behind it with a satisfaction guarantee - try it risk-free and see the results for yourself!
        </p>
        <button className="banner-button">GET STARTED</button>
      </div>
    </header>
  );
};

export default Header;