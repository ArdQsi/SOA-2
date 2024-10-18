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

        axios.delete(`http://localhost:8080/persons/${id}`)
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
                <InputField name="Id" value={id} type="text" setState={setId} />
                <button type="submit">Delete person</button>
            </form>
            <div className='info'> {info} </div>
            <div className='error'> {error} </div>
        </>
    );
};

export default DeletePerson;