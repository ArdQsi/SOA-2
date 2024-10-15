import React, { useState, useEffect } from "react";
import axios from "axios";

const CreatePerson = () => {
  const [people, setPeople] = useState([]);
  const [person, setPerson] = useState({});
  const [filter, setFilter] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchPeople = async () => {
      setIsLoading(true);
      try {
        const response = await axios.get("/api/people");
        setPeople(response.data);
      } catch (error) {
        setError(error);
      } finally {
        setIsLoading(false);
      }
    };
    fetchPeople();
  }, []);

  const handleCreatePerson = async (newPerson) => {
    try {
      const response = await axios.post("/api/people", newPerson);
      setPeople([...people, response.data]);
    } catch (error) {
      setError(error);
    }
  };

  const handleDeletePerson = async (id) => {
    try {
      await axios.delete(`/api/people/${id}`);
      setPeople(people.filter((p) => p.id !== id));
    } catch (error) {
      setError(error);
    }
  };

  const handleUpdatePerson = async (updatedPerson) => {
    try {
      const response = await axios.put(`/api/people/${updatedPerson.id}`, updatedPerson);
      setPeople(people.map((p) => (p.id === updatedPerson.id ? response.data : p)));
    } catch (error) {
      setError(error);
    }
  };

  const handleGetPersonById = async (id) => {
    try {
      const response = await axios.get(`/api/people/${id}`);
      setPerson(response.data);
    } catch (error) {
      setError(error);
    }
  };

  const handleGetPeopleByFilter = async () => {
    try {
      const response = await axios.get(`/api/people?filter=${filter}`);
      setPeople(response.data);
    } catch (error) {
      setError(error);
    }
  };

  const handleFilterChange = (event) => {
    setFilter(event.target.value);
  };
  return (
    <>
    <h1>People</h1>
      {isLoading && <p>Loading...</p>}
      {error && <p>Error: {error.message}</p>}
      <div>
        <input type="text" value={filter} onChange={handleFilterChange} placeholder="Filter by..." />
        <button onClick={handleGetPeopleByFilter}>Filter</button>
      </div>
      <ul>
        {people.map((p) => (
          <li key={p.id}>
            {p.name} - {p.eyeColor}
            <button onClick={() => handleDeletePerson(p.id)}>Delete</button>
            <button onClick={() => handleGetPersonById(p.id)}>Edit</button>
          </li>
        ))}
      </ul>
      <h2>Person Details</h2>
      {person.id && (
        <div>
          <p>Name: {person.name}</p>
          <p>Eye Color: {person.eyeColor}</p>
          <button onClick={() => handleUpdatePerson(person)}>Update</button>
        </div>
      )}
      <h2>Create Person</h2>
      <form
        onSubmit={(e) => {
          e.preventDefault();
          const newPerson = {
            name: e.target.name.value,
            eyeColor: e.target.eyeColor.value,
          };
          handleCreatePerson(newPerson);
          e.target.reset();
        }}
      >
        <label htmlFor="name">Name:</label>
        <input type="text" id="name" name="name" />
        <label htmlFor="eyeColor">Eye Color:</label>
        <input type="text" id="eyeColor" name="eyeColor" />
        <button type="submit">Create</button>
      </form>
      {/* <div>Создать человека</div>
      <form onSubmit={handleSubmit}>
        <div>
          <label>
            Название:
            <input
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            />
          </label>
        </div>
        <button type="submit">Создать</button>
      </form> */}
    </>
  );
};

export default CreatePerson;