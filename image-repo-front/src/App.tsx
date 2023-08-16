import React from 'react';
import "./App.css";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Header, Footer, SplashScreen } from './components';
import { AboutPage, HomePage, NotFoundPage, ImagesPage, ImagePage, ProfilePage, LoginPage, SignUpPage } from './pages'
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import AuthProvider from './contexts/AuthProvider';
import SplashScreenProvider from './contexts/SplashScreenProvider';

export interface IApplicationProps {}

const App: React.FunctionComponent<IApplicationProps> = (props) => {
    return (
        <>
            <SplashScreenProvider>
                <AuthProvider>
                        <BrowserRouter>
                            <Header />
                            <Routes>
                                <Route path="/" element={<HomePage />} />
                                <Route path='images'>
                                    <Route index element={<ImagesPage />} />
                                    <Route path=':mediaId' element={<ImagePage />} />
                                </Route>
                                <Route path="about" element={<AboutPage />} />
                                <Route path="profile" element={<ProfilePage />} />
                                <Route path="login" element={<LoginPage />} />
                                <Route path="signup" element={<SignUpPage />} />
                                <Route path="*" element={<NotFoundPage />} />
                            </Routes>
                            <Footer />
                        </BrowserRouter>
                </AuthProvider>
                <SplashScreen />
            </SplashScreenProvider>
            <ToastContainer/>
        </>
    );
};

export default App;