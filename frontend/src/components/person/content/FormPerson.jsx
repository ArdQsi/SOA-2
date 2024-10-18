import React from 'react';
import UpdatePerson from './UpdatePerson'
import CreatePerson from './CreatePerson';
import DeletePerson from './DeletePerson';
import SearchPerson from './SearchPerson';
import FilterPerson from './FilterPerson';
import CountPerson from './CountPerson';

const FormPerson = ({ activeForm }) => {
    switch (activeForm) {
        case 'create':
            return <CreatePerson />;
        case 'update':
            return <UpdatePerson />;
        case 'search':
            return <SearchPerson/>;
        case 'delete':
            return <DeletePerson/>;
        case 'filter':
            return <FilterPerson/>
        case 'count':
            return <CountPerson/>
        default:
            return null;
    }
};

export default FormPerson;