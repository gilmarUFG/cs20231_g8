import React, { createContext, useState, useEffect, useContext, ReactNode } from 'react';

type SplashScreenProviderProps = {
    children: ReactNode;
}

type SplashScreenContextData = {
  show: boolean;
  start: () => void;
  stop: () => void;
};

const SplashScreenContext = createContext<SplashScreenContextData>({} as SplashScreenContextData);

export const useSplashScreen = () => useContext(SplashScreenContext);

const SplashScreenProvider: React.FunctionComponent<SplashScreenProviderProps> = ({ children }) => {

  const [show, setShow] = useState(false);

  const start = (): void => {
    setShow(true);
  };

  const stop = (): void => {
    setShow(false);
  };

  useEffect(() => {
    if (show) {
        setShow(true);
    }
  }, []);

  return (
    <SplashScreenContext.Provider value={{ show, start, stop }}>
      {children}
    </SplashScreenContext.Provider>
  );
};

export default SplashScreenProvider;