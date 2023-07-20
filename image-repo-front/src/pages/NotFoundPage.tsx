import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

export interface NotFoundPageProps {}

const NotFoundPage: React.FunctionComponent<NotFoundPageProps> = (props) => {
    const navigate = useNavigate();

    return (
        <div>
        <h2>404 Page not found</h2>
      </div>
      );
};

export default NotFoundPage;