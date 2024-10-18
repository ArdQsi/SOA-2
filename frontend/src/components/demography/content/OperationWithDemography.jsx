import React, { useState } from 'react';
import './style.css'
import FormDemography from './FormDemography'

const OperationWithDemography = () => {
  const [activeForm, setActiveForm] = useState('percentage');

  return (
    <div className='container'>
      <div className='buttonsCenter'>
        <button className={`buttonChange ${activeForm === 'percentage' ? "active" : " "}`} onClick={() => setActiveForm('percentage')}>Percentage</button>
        /
        <button className={`buttonChange ${activeForm === 'count' ? "active" : " "}`} onClick={() => setActiveForm('count')}>Count</button>
      </div>
      <FormDemography activeForm={activeForm}/>
    </div>
  );
};

export default OperationWithDemography;