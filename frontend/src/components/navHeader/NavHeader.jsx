import React from 'react';
import './style.css';
import { NavLink } from 'react-router-dom';

const NavHeader = (e) => {
  return (
    <header >
      <nav>
        <ul>
          <li >
            <NavLink to="/" className={({ isActive }) => (isActive ? 'navLink activePage' : "navLink")} >Person</NavLink>
          </li>
          <li>
            <NavLink to="/demography" className={({ isActive }) => (isActive ? 'navLink activePage' : "navLink")}> Demography </NavLink>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default NavHeader;