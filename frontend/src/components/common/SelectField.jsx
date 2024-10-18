import React from "react";

const SelectField = ({ name, value, options, setState, required = true }) => {

    return (
        <div className="input-container">
            <label for={value}> {name}</label>
                <select name={name} id={value} value={value} onChange={(e) => setState(e.target.value)}
                    required={required}
                >
                    {options.map((option) => (
                        <option key={option.value} value={option.value}>
                            {option.label}
                        </option>
                    ))}
                </select>
            
        </div>
    );
};

export default SelectField;