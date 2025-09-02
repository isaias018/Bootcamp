import React, { useState } from 'react';
import './ExpandableList.css';

const ExpandableList = () => {
  const [expandedItem, setExpandedItem] = useState(null);
  
  const items = [
    {
      title: "Holdback",
      subtitle: "Joint and Several Liability",
      content: "Holdback estimates the point at which a well turns from an asset to a liability. Using a basic cash flow model that integrates estimated plugging costs, holdback identifies the moment in time when all future undiscounted cash flows must be 'held back' to cover the cost to retire the well. Lorem Ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. At consectetur lorem donee massa sapien faucibus et. Fusce ut placerat orci nulla"
    },
    {
      title: "Financial Assurance",
      subtitle: "Colorado",
      content: "Financial assurance content would go here..."
    },
    {
      title: "Liability Ringfencing",
      subtitle: "Joint and Several Liability",
      content: "Liability ringfencing content would go here..."
    },
    {
      title: "ARO Creditor Rights",
      subtitle: "ARO Moral Hazard",
      content: "ARO creditor rights content would go here..."
    }
  ];
  
  return (
    <section className="section">
      <h3 className="component-title">Listado de elementos expandibles</h3>
      <div className="expandable-list">
        {items.map((item, index) => (
          <div key={index} className="expandable-item">
            <div 
              className="expandable-header"
              onClick={() => setExpandedItem(expandedItem === index ? null : index)}
            >
              <div>
                <div className="item-title">{item.title}</div>
                <div className="item-subtitle">{item.subtitle}</div>
              </div>
              <span className="arrow">
                {expandedItem === index ? '▼' : '►'}
              </span>
            </div>
            {expandedItem === index && (
              <div className="expandable-content">
                {item.content}
              </div>
            )}
          </div>
        ))}
      </div>
    </section>
  );
};

export default ExpandableList;