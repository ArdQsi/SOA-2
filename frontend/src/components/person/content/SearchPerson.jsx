import React, { useState } from 'react';
import axios from "axios";
import InputField from '../../common/InputField';
import './style.css'

const SearchPerson = () => {
    const [id, setId] = useState('');
    const [person, setPerson] = useState(null);
    const [error, setError] = useState(null);

    const handleSearchSubmit = async (e) => {
        e.preventDefault();
        axios.get(`https://localhost:8598/person-service-1.0-SNAPSHOT/persons/${id}`)
            .then(function (response) {
                if (response.data !== undefined){
                    setPerson(response.data)
                    setError(null)
                }
                
            })
            .catch(function (error) {
                setPerson(null)
                setError(error.response.data.message)
            });
    };
    return (
        <>
            <h1>Search person</h1>
            <form onSubmit={handleSearchSubmit}>
                <InputField name="Id" value={id} type="number" setState={setId} />
                <button type="submit">Search person</button>
            </form>
            <div className='error'> {error} </div>
            {person && (
                <div>
                    <h1>Person Details</h1>
                    <div>ID: {person.id}</div>
                    <div>Name: {person.name}</div>
                    <div>Coordinates: ({person.coordinates.x}, {person.coordinates.y})</div>
                    <div>Creation Date: {person.creationDate}</div>
                    <div>Height: {person.height}</div>
                    <div>Birthday: {person.birthday}</div>
                    <div>Passport ID: {person.passportID}</div>
                    <div>Eye Color: {person.eyeColor}</div>
                    <div>Nationality: {person.nationality}</div>
                    <div>
                        Location: ({person.location.x}, {person.location.y}, {person.location.z})
                    </div>
                </div>
            )}
        </>
    );
};

export default SearchPerson;