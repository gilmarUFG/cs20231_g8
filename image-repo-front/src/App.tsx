import React from 'react';
import "./App.css";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import {LayoutComponent, NavbarComponent} from './components';
import {AboutPage, HomePage, TestPage, NotFoundPage} from './pages'

export interface IApplicationProps {}

const App: React.FunctionComponent<IApplicationProps> = (props) => {
    return (
        <BrowserRouter>
            <NavbarComponent/>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="about">
                    <Route index element={<AboutPage />} />
                    <Route path=":number" element={<AboutPage />} />
                </Route>
                <Route path="test" element={<TestPage />} />
                <Route path="layout" element={<LayoutComponent />}>
                    <Route index element={<AboutPage />} />
                    <Route path=":number" element={<AboutPage />} />
                </Route>
                <Route path="*" element={<NotFoundPage />} />
            </Routes>
        </BrowserRouter>

    );
};

export default App;