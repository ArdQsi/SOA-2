import React from 'react';

const InputField = ({ name, id, type, step=1, setState, required = true }) => {
    return (
        <div className="input-container">
            <label for={id}>{name}</label>
            <input type={type} step={step} id={id} value={id} onChange={(e) => setState(e.target.value)} required={required} />
        </div>
    );
};

export default InputField;