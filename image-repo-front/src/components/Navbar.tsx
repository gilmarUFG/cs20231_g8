import React from 'react';
import { NavLink } from 'react-router-dom';
import "./Navbar.css";

export interface INavbarComponentProps {}

const NavbarComponent: React.FunctionComponent<INavbarComponentProps> = (props) => {
    return (
        <nav className="nav-container">
        <ul className="nav-unordered-list">
          <li className="nav-list-item">
            <NavLink to="/">Home</NavLink>
          </li>
          <li className="nav-list-item">
            <NavLink to="/about">About</NavLink>
          </li>
          <li className="nav-list-item">
            <NavLink to="/test">Test</NavLink>
          </li>
        </ul>
      </nav>
      );
};

export default NavbarComponent;