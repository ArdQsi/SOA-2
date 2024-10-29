import React, { useState } from 'react';
import axios from "axios";
import InputField from '../../common/InputField';
import './style.css'

const DeletePerson = () => {
    const [id, setId] = useState("");
    const [error, setError] = useState("");
    const [info, setInfo] = useState("");

    const handleDeleteSubmit = async (e) => {
        e.preventDefault();

        axios.delete(`https://localhost:8598/person-service-1.0-SNAPSHOT/persons/${id}`)
            .then(function (response) {
                setInfo(response.data)
                setError(null)
            })
            .catch(function (error) {
                setInfo(null)
                setError(error.response.data)
            });
    };
    return (
        <>
            <h1>Delete person</h1>
            <form onSubmit={handleDeleteSubmit}>
                <InputField name="Id" value={id} type="number" setState={setId} />
                <button type="submit">Delete person</button>
            </form>
            <div className='info'> {info} </div>
            <div className='error'> {error} </div>
        </>
    );
};

export default DeletePerson;