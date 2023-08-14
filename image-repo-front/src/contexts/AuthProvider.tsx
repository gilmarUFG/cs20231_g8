import React, { createContext, useState, useEffect, useContext, ReactNode } from 'react';
import User from '../models/user.model';
import { authenticated, getToken } from '../api/services/auth.service';

type AuthProviderProps = {
    children: ReactNode;
}

type AuthContextData = {
  isAuthenticated: boolean;
  login: () => void;
  logout: () => void;
  user: User;
};

const AuthContext = createContext<AuthContextData>({} as AuthContextData);

export const useAuth = () => useContext(AuthContext);

const AuthProvider: React.FunctionComponent<AuthProviderProps> = ({ children }) => {

  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const [user, setUser] = useState(new User());

  const login = (): void => {
    setIsAuthenticated(true);
  };

  const logout = (): void => {
    setIsAuthenticated(false);
  };

  useEffect(() => {
    if (authenticated()) {
      setIsAuthenticated(true);
    }
  }, []);

  return (
    <AuthContext.Provider value={{ isAuthenticated, login, logout, user }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;