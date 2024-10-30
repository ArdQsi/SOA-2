import React, { useState } from 'react';
import axios from "axios";
import InputField from '../../common/InputField';
import SelectField from '../../common/SelectField'
import './style.css'

const CreatePerson = () => {
    const [name, setName] = useState("");
    const [x, setX] = useState("");
    const [y, setY] = useState("");
    const [height, setHeight] = useState("");
    const [birthday, setBirthday] = useState("");
    const [passportID, setPassportID] = useState("");
    const [nationality, setNationality] = useState("RUSSIAN");
    const [eyeColor, setEyeColor] = useState("GREEN");
    const [locationX, setLocationX] = useState("");
    const [locationY, setLocationY] = useState("");
    const [locationZ, setLocationZ] = useState("");
    const [error, setError] = useState("");
    const [info, setInfo] = useState("");

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


    const handleCreateSubmit = async (e) => {
        e.preventDefault();
        const newPerson = {
            name,
            coordinates: {
                x: parseFloat(x),
                y: parseFloat(y),
            },
            height: parseFloat(height),
            birthday,
            passportID,
            eyeColor,
            nationality,
            location: {
                x: parseFloat(locationX),
                y: parseFloat(locationY),
                z: parseFloat(locationZ),
            },
        };

        axios.post('https://localhost:8598/person-service-1.0-SNAPSHOT/persons', newPerson)
            .then(function (response) {
                setError(null)
                setInfo("Creation is successful")
            })
            .catch(function (error) {
                setInfo(null)
                setError(error.response.data.message)
            });
    };
    return (
        <div className='form-container'>
            <h1>Create person</h1>
            <form onSubmit={handleCreateSubmit}>
                <InputField name="Name" value={name} type="text" setState={setName} />
                <InputField name="Coordinates X" value={x} type="number" step="0.01" setState={setX} />
                <InputField name="Coordinates Y" value={y} type="number" step="0.01" setState={setY} />
                <InputField name="Height" value={height} type="number" step="0.01" setState={setHeight} />
                <InputField name="Birthday" value={birthday} type="datetime-local" setState={setBirthday} />
                <InputField name="Passport ID" value={passportID} type="text" setState={setPassportID} />
                <SelectField name="Nationality" value={nationality} options={nationalityOptions} setState={setNationality} />
                <SelectField name="Eye color" value={eyeColor} options={eyeColorOptions} setState={setEyeColor} />
                <InputField name="Location X" value={locationX} type="number" step="0.01" setState={setLocationX} />
                <InputField name="Location Y" value={locationY} type="number" step="0.01" setState={setLocationY} />
                <InputField name="Location Z" value={locationZ} type="number" step="0.01" setState={setLocationZ} />
                <button type="submit">Create person</button>
            </form>
            <div className='error'> {error} </div>
            <div className='info'> {info} </div>
        </div>
    );
};

export default CreatePerson;