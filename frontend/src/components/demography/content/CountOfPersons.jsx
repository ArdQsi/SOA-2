import React, { useState } from 'react';
import axios from "axios";
import './style.css'
import SelectField from '../../common/SelectField';

const CountOfPersons = () => {
    const [eyeColor, setEyeColor] = useState("GREEN");
    const [nationality, setNationality] = useState("RUSSIAN");
    const [count, setCount] = useState("");
    const [error, setError] = useState("");

    const eyeColorOptions = [
        { value: "GREEN", label: "GREEN" },
        { value: "BLACK", label: "BLACK" },
        { value: "BLUE", label: "BLUE" },
        { value: "ORANGE", label: "ORANGE" },
        { value: "BROWN", label: "BROWN" }
    ];

    const nationalityOptions = [
        { value: "RUSSIAN", label: "RUSSIAN" },
        { value: "CHINESE", label: "CHINESE" },
        { value: "AMERICAN", label: "AMERICAN" },
        { value: "JAPANESE", label: "JAPANESE" },
        { value: "GERMAN", label: "GERMAN" }
    ];

    const handleCountSubmit = async (e) => {
        e.preventDefault();

        axios.get(`https://localhost:8444/demography/nationality/${nationality}/eye-color/${eyeColor}`)
            .then(function (response) {
                setCount(response.data.count)
                setError(null)
            })
            .catch(function (error) {
                setError(error.message)
            });
    };
    return (
        <>
            <h1>Count of persons</h1>
            <form onSubmit={handleCountSubmit}>
                <SelectField name="eyeColor" value={eyeColor} options={eyeColorOptions} setState={setEyeColor} />
                <SelectField name="nationality" value={nationality} options={nationalityOptions} setState={setNationality} />
                <button type="submit">Count of person with eyeColor {eyeColor} and nationality {nationality}</button>
            </form>
            {count}
            <div className='error'> {error} </div>
        </>
    );
};

export default CountOfPersons;