import React, { useState } from 'react';
import axios from "axios";
import './style.css'
import SelectField from '../../common/SelectField'

const PercentageOfPersons = () => {
    const [eyeColor, setEyeColor] = useState("GREEN");
    const [percentage, setPercentage] = useState("");
    const [error, setError] = useState("");

    const eyeColorOptions = [
        { value: "GREEN", label: "GREEN" },
        { value: "BLACK", label: "BLACK" },
        { value: "BLUE", label: "BLUE" },
        { value: "ORANGE", label: "ORANGE" },
        { value: "BROWN", label: "BROWN" }
    ];

    const handleCountSubmit = async (e) => {
        e.preventDefault();

        axios.get(`https://localhost:8444/demography/${eyeColor}/percentage`)
            .then(function (response) {
                setPercentage(response.data)
                setError(null)
            })
            .catch(function (error) {
                setError(error.message)
            });
    };
    return (
        <>
            <h1>Percentage of persons</h1>
            <form onSubmit={handleCountSubmit}>
                <SelectField name="eyeColor" value={eyeColor} options={eyeColorOptions} setState={setEyeColor} />
                <button type="submit">Get percentage of person, where eye color less than {eyeColor}</button>
            </form>
            {percentage && (
                <>
                    {percentage}
                </>
            )}
            <div className='error'> {error} </div>
        </>
    );
};

export default PercentageOfPersons;