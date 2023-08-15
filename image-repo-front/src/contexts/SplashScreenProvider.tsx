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

  useEffect(() => {
    if (show) {
        setShow(true);
    }
  }, []);

  useEffect(() => {
    if (show)
      document.body.style.overflow = 'hidden';
    return () => {
      document.body.style.overflow = 'unset';
    };
  }, [show]);

  const start = (): void => {
    setShow(true);
  };

  const stop = (): void => {
    setShow(false);
  };

  return (
    <SplashScreenContext.Provider value={{ show, start, stop }}>
      {children}
    </SplashScreenContext.Provider>
  );
};

export default SplashScreenProvider;