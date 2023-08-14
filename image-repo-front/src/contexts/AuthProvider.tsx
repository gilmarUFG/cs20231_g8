import React, { createContext, useState, useEffect, useContext, ReactNode } from 'react';
import User from '../models/user.model';
import { authenticated, clearToken, getToken, setToken } from '../api/services/auth.service';
import { findCurrentUser } from '../api/services/user.service';
import { toast } from 'react-toastify';

type AuthProviderProps = {
    children: ReactNode;
}

type AuthContextData = {
  isAuthenticated: boolean;
  login: (token: string) => void;
  logout: () => void;
  user: User;
};

const AuthContext = createContext<AuthContextData>({} as AuthContextData);

export const useAuth = () => useContext(AuthContext);

const AuthProvider: React.FunctionComponent<AuthProviderProps> = ({ children }) => {

  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const [user, setUser] = useState(new User());

  const login = (token: string): void => {
    setToken(token);
    findCurrentUser()
      .then(
        response => {
          setUser(response.data);
          setIsAuthenticated(true);
        }
      )
      .catch(
        error => toast.error(error.message)
      )
  };

  const logout = (): void => {
    setIsAuthenticated(false);
    clearToken();
  };

  useEffect(() => {
    if (authenticated()) {
      login(getToken());
    }
  }, []);

  return (
    <AuthContext.Provider value={{ isAuthenticated, login, logout, user }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;