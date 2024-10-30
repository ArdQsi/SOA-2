import React, { useState } from 'react';
import axios from 'axios';
import InputField from '../../common/InputField';
import SelectField from '../../common/SelectField'

const FilterPerson = () => {
    const [people, setPeople] = useState([]);
    const [pageNumber, setPageNumber] = useState(1);
    const [pageSize, setPageSize] = useState(10);
    const [id, setId] = useState("");
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
    const [creationDate, setCreationDate] = useState("");
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

    const handleFilterSubmit = async (e, apiEndpoints) => {
        e.preventDefault();

        axios.get(apiEndpoints)
            .then(function (response) {
                setError(null)
                setPeople(response.data);
            })
            .catch(function (error) {
                if (error.response.data === ""){
                    setError("You must specify height for filtering")
                } else {
                    setError(error.response.data.message)
                }
            });
    }

    const handleFilterMainSubmit = async (e) => {
        e.preventDefault();

        const url = new URL("https://localhost:8598/person-service-1.0-SNAPSHOT/persons/filter");
        url.searchParams.append("page-number", pageNumber);
        url.searchParams.append("page-size", pageSize);
        if (id) url.searchParams.append("id", id);
        if (name) url.searchParams.append("name", name);
        if (x) url.searchParams.append("coordinate-x", x);
        if (y) url.searchParams.append("coordinate-y", y);
        if (creationDate) url.searchParams.append("creation-date", creationDate);
        if (height) url.searchParams.append("height", height);
        if (birthday) url.searchParams.append("birthday", birthday);
        if (passportID) url.searchParams.append("passport-id", passportID);
        if (eyeColor) url.searchParams.append("eye-color", eyeColor);
        if (nationality) url.searchParams.append("nationality", nationality);
        if (locationX) url.searchParams.append("location-x", locationX);
        if (locationY) url.searchParams.append("location-y", locationY);
        if (locationZ) url.searchParams.append("location-z", locationZ);

        axios.get(url.toString())
            .then(function (response) {
                setError(null)
                setPeople(response.data);
            })
            .catch(function (error) {
                setPeople(null);
                setError(error.response.data)
            });
    };

    return (
        <div>
            <h1>Filter of persons</h1>
            <form>
                <InputField name="Page number" value={pageNumber} type="number" setState={setPageNumber} required={false} />
                <InputField name="Page size" value={pageSize} type="number" setState={setPageSize} required={false} />
                <InputField name="Id" value={id} type="number" setState={setId} required={false} />
                <InputField name="Name" value={name} type="text" setState={setName} required={false} />
                <InputField name="Coordinates X" value={x} type="number" step="0.01" setState={setX} required={false} />
                <InputField name="Coordinates Y" value={y} type="number" step="0.01" setState={setY} required={false} />
                <InputField name="Height" value={height} type="number" step="0.01" setState={setHeight} required={false} />
                <InputField name="Birthday" value={birthday} type="datetime-local" setState={setBirthday} required={false} />
                <InputField name="Creation Date" value={creationDate} type="datetime-local" setState={setCreationDate} required={false} />
                <InputField name="Passport ID" value={passportID} type="text" setState={setPassportID} required={false} />
                <SelectField name="Nationality" value={nationality} options={nationalityOptions} setState={setNationality} required={false} />
                <SelectField name="Eye color" value={eyeColor} options={eyeColorOptions} setState={setEyeColor} required={false} />
                <InputField name="Location X" value={locationX} type="number" step="0.01" setState={setLocationX} required={false} />
                <InputField name="Location Y" value={locationY} type="number" step="0.01" setState={setLocationY} required={false} />
                <InputField name="Location Z" value={locationZ} type="number" step="0.01" setState={setLocationZ} required={false} />
                <button type="submit" onClick={handleFilterMainSubmit}>Filter persons</button>
                <button type="submit" onClick={(event) => handleFilterSubmit(event, `https://localhost:8598/person-service-1.0-SNAPSHOT/persons/filter/less/${eyeColor}`)}>Filter persons with eye color less than {eyeColor}</button>
                <button type="submit" onClick={(event) => handleFilterSubmit(event, `https://localhost:8598/person-service-1.0-SNAPSHOT/persons/filter/more/${height}`)}>Filter persons with height greater than {height}</button>
            </form>

            {error ? <div className='error'> {error} </div > :
                <div className="full-width-table">
                    <h1>Filtered persons</h1>
                    <table className="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Coordinates</th>
                                <th>Creation Date</th>
                                <th>Height</th>
                                <th>Birthday</th>
                                <th>Passport ID</th>
                                <th>Eye Color</th>
                                <th>Location</th>
                            </tr>
                        </thead>
                        <tbody>
                            {people.map((person) => (
                                <tr key={person.id}>
                                    <td>{person.id}</td>
                                    <td>{person.name}</td>
                                    <td>({person.coordinates.x}, {person.coordinates.y})</td>
                                    <td>{person.creationDate}</td>
                                    <td>{person.height}</td>
                                    <td>{person.birthday}</td>
                                    <td>{person.passportID}</td>
                                    <td>{person.eyeColor}</td>
                                    <td>({person.location.x}, {person.location.y}, {person.location.z})</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>}
        </div>
    );
};

export default FilterPerson;