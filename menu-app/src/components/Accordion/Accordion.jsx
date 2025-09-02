import React, { useState } from 'react';
import './Accordion.css';

const Accordion = () => {
  const [openItems, setOpenItems] = useState([]);
  
  const items = [
    {
      title: "Label 1",
      content: "We created a 'Task List' feature that provides you with an organized view of all your pending tasks, with options to set recurring tasks weekly, monthly, or as often as you'd like."
    },
    {
      title: "Label 2",
      content: "Anyone from advisors to management can use a Task List to check off whatever has been completed and manage their schedules accordingly."
    },
    {
      title: "Label 3",
      content: "It also provides incredible team alignment, ensuring that nothing slips through the cracks when someone is out."
    }
  ];
  
  const toggleItem = (index) => {
    if (openItems.includes(index)) {
      setOpenItems(openItems.filter(i => i !== index));
    } else {
      setOpenItems([...openItems, index]);
    }
  };
  
  return (
    <section className="section">
      <h3 className="component-title">Componente acordeón</h3>
      <div className="accordion">
        {items.map((item, index) => (
          <div key={index} className="accordion-item">
            <div 
              className={`accordion-header ${openItems.includes(index) ? 'open' : ''}`}
              onClick={() => toggleItem(index)}
            >
              {item.title}
              <span className="accordion-arrow">
                {openItems.includes(index) ? '▼' : '►'}
              </span>
            </div>
            {openItems.includes(index) && (
              <div className="accordion-content">
                {item.content}
              </div>
            )}
          </div>
        ))}
      </div>
    </section>
  );
};

export default Accordion;