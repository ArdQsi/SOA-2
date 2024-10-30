import React, { useState } from 'react';
import axios from "axios";
import SelectField from '../../common/SelectField'
import './style.css'

const CreatePerson = () => {
    const [eyeColor, setEyeColor] = useState("GREEN");
    const [amount, setAmount] = useState("");
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

        axios.get(`https://localhost:8598/person-service-1.0-SNAPSHOT/persons/count/less/${eyeColor}`)
            .then(function (response) {
                setError(null)
                setAmount(response.data)
            })
            .catch(function (error) {
                setAmount(null)
                setError(error.response.data.message)
            });
    };
    return (
        <>
            <h1>Count of persons</h1>
            <form onSubmit={handleCountSubmit}>
                <SelectField name="Eye color" value={eyeColor} options={eyeColorOptions} setState={setEyeColor} />
                <button type="submit">Count persons with eye color less than {eyeColor}</button>
            </form>
            {amount}
            <div className='error'> {error} </div>
        </>
    );
};

export default CreatePerson;