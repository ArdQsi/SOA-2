import React from 'react';

const InputField = ({ name, id, type, setState, required = true }) => {
    return (
        <div className="input-container">
            <label for="id">{name}</label>
            <input type={type} id={id} value={id} onChange={(e) => setState(e.target.value)} required={required} />
        </div>
    );
};

export default InputField;