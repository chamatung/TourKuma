import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {GoogleLogin, GoogleOAuthProvider} from "@react-oauth/google";
import jwt from "jsonwebtoken";

const Test = () => {
    const [code, setCode] = useState('');

    const kakaoTest = () => {
        const REST_API_KEY = '7179ae74cbd07a526a748fa81d0dc99f'; // REST API KEY
        const REDIRECT_URI = 'http://localhost:3000/'; // Redirect URI
        // oauth 요청 URL
        const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;
        const handleLogin = () => {
            window.location.href = kakaoURL;
            console.log('test success');
        };
        handleLogin();
    };

    const sendCode = () => {
        if (code) {
            axios
                .post('http://localhost:8085/api/v1/client/auth', {
                    code: code,
                })
                .then((response) => {
                    console.log(response);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    };
    const clientId = '926356944317-q33132juq227qs8s6kdvpp2e99na30ak.apps.googleusercontent.com'


    useEffect(() => {
        sendCode();
    }, [code]);

    useEffect(() => {
        const params = new URL(document.location.toString()).searchParams;
        const authCode = params.get('code');
        if (authCode) {
            setCode(authCode);
        }
    }, []);

    return (
        <>
            <div>
                <img
                    onClick={() => kakaoTest()}
                    src={process.env.PUBLIC_URL + '/kakao_login.png'}
                />
            </div>
            <GoogleOAuthProvider clientId={clientId}>
                <GoogleLogin
                    onSuccess={(res) => {
                        console.log(res);






                    }}
                    onFailure={(err) => {
                        console.log("error : " + err);
                    }}
                />
            </GoogleOAuthProvider>
        </>
    );
};

export default Test;
