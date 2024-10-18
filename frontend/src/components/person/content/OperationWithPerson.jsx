import React, { useState } from 'react';
import FormPerson from './FormPerson';
import './style.css'

const OperationWithPerson = () => {
  const [activeForm, setActiveForm] = useState('create');

  return (
    <div className='container'>
      <div className='buttonsCenter'>
        <button className={`buttonChange ${activeForm === 'create' ? "active" : " "}`} onClick={() => setActiveForm('create')}>Create</button>
        /
        <button className={`buttonChange ${activeForm === 'update' ? "active" : " "}`} onClick={() => setActiveForm('update')}>Update</button>
        /
        <button className={`buttonChange ${activeForm === 'search' ? "active" : " "}`} onClick={() => setActiveForm('search')}>Search</button>
        /
        <button className={`buttonChange ${activeForm === 'delete' ? "active" : " "}`} onClick={() => setActiveForm('delete')}>Delete</button>
        /
        <button className={`buttonChange ${activeForm === 'filter' ? "active" : " "}`} onClick={() => setActiveForm('filter')}>Filter</button>
        /
        <button className={`buttonChange ${activeForm === 'count' ? "active" : " "}`} onClick={() => setActiveForm('count')}>Count</button>
      </div>
      <FormPerson activeForm={activeForm}/>
    </div>
  );
};

export default OperationWithPerson;