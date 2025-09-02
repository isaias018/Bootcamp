import React from 'react';
import Header from './components/Header/Header';
import ExpandableList from './components/ExpandableList/ExpandableList';
import CardComponent from './components/CardComponent/CardComponent';
import Accordion from './components/Accordion/Accordion';
import ButtonGallery from './components/ButtonGallery/ButtonGallery';
import Footer from './components/Footer/Footer';
import './App.css';

function App() {
  return (
    <div className="App">
      <Header />
      <div className="content">
        <h2 className="section-title">Componentes Solicitados</h2>
        
        <ExpandableList />
        <CardComponent />
        <Accordion />
        <ButtonGallery />
      </div>
      <Footer />
    </div>
  );
}

export default App;