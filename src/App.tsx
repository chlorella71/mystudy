import React from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
import './App.css'

import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Header from './Components/Header';
import MainScreen from './Pages/MainScreen.tsx';
import SignupScreen from "./Pages/SignupScreen.tsx";
import Footer from "./Components/Footer.tsx";
import Signup2Screen from "./Pages/Signup2Screen.tsx";
import TravelSubmissionScreen from "./Pages/TravelSubmissionScreen.tsx";
import LoginScreen from "./Pages/LoginScreen.tsx";
import MypageScreen from "./Pages/MypageScreen.tsx";
import Item1 from "./Pages/Item1.tsx";
import Item2 from "./Pages/Item2.tsx";

const App: React.FC = () => {
    return <>
        <Router>
            <Header />
            {/*<MainScreen />*/}
            <Routes>
                <Route path="/" element={<MainScreen />} />
                <Route path="/Pages/SignupScreen" element={<SignupScreen />}/>
                <Route path="/Pages/Signup2Screen" element={<Signup2Screen />} />
                <Route path="/Pages/TravelSubmissionScreen" element={<TravelSubmissionScreen />} />
                <Route path="/Pages/LoginScreen" element={<LoginScreen />} />
                <Route path="/Pages/MypageScreen" element={<MypageScreen />} />
                <Route path="/item1" element={<Item1 />} />
                <Route path="/item2" element={<Item2 />} />
            </Routes>
            <Footer />
        </Router>
        </>
}

export default App
