import React from 'react';
import './CardComponent.css';

const CardComponent = () => {
  return (
    <section className="section">
      <h3 className="component-title">Card con imagen, texto y links</h3>
      <div className="card">
        <div className="card-image">
          <div className="image-placeholder">Oilfield Image</div>
        </div>
        <div className="card-content">
          <h3 className="card-title">It's Closing Time: The Huge Bill to Abandon Oilfields Comes Early</h3>
          <p className="card-text">
            Billion Dollar Orphans estimate that plugging 2.6 million documented onshore wells in the U.S. alone will cost $280 billion. 
            This estimate excludes costs to plug an additional estimated 1.2 million undocumented onshore wells.
          </p>
          
          <div className="card-table">
            <div className="table-row">
              <div className="table-header">STATE</div>
              <div className="table-header">COST</div>
              <div className="table-header">ORPHAN</div>
            </div>
            <div className="table-row">
              <div className="table-cell">4. ago 2022</div>
              <div className="table-cell"></div>
              <div className="table-cell"></div>
            </div>
          </div>
          
          <div className="card-links">
            <a href="#read" className="card-link">READ REPORT »</a>
            <a href="#download" className="card-link">DOWNLOAD REPORT »</a>
          </div>
        </div>
      </div>
    </section>
  );
};

export default CardComponent;