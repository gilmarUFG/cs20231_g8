import React from 'react';
import { styled } from 'styled-components';
import { AdvantagesSection, CalloutSection, PresentationSection } from '../components';

export interface IHomePageProps {};

const HomePage: React.FunctionComponent<IHomePageProps> = (props) => {

    return (
        <>
            <PresentationSection />
            <AdvantagesSection />
            <CalloutSection />
        </>
    );
};

export default HomePage;