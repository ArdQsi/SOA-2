import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Person from './components/person/page/PersonPage.jsx'
import Demography from './components/demography/page/DemographyPage.jsx'
import { Routes, Route, HashRouter } from 'react-router-dom'
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <HashRouter>
    <Routes>
      <Route path='/' element={<Person />} />
      <Route path='/demography' element={<Demography />} />
    </Routes>
  </HashRouter>
)
