import React from 'react';
import CountOfPersons from './CountOfPersons'
import PercentageOfPersons from './PercentageOfPersons';

const FormDemography = ({ activeForm }) => {
    switch (activeForm) {
        case 'percentage':
            return <PercentageOfPersons />;
        case 'count':
            return <CountOfPersons/>;
        default:
            return null;
    }
};

export default FormDemography;