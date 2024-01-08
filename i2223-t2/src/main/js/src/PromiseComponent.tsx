import React, {useEffect, useState} from 'react';
import {createRoot} from "react-dom/client";

type PromiseProps = {
    f: () => Promise<string>
}

function PromiseComponent({f}: PromiseProps) {
    const [counter, setCounter] = useState<number>(0);
    const [result, setResult] = useState<string>("");

    useEffect(() => {
        f().then((value) => setResult(value), (reason) => setResult(reason));
    }, [f]);

    useEffect(() => {
        if (result === "") {
            const intervalID = setInterval(() => {
                setCounter((c) => c + 1);
            }, 100)
            return () => clearInterval(intervalID);
        }
    }, [result]);

    return (
        <div>
            <p>{result || counter}</p>
        </div>

    )
}

export function demo() {
    const root = createRoot(document.getElementById("container"))

    function promiseGiver() {
        return new Promise<string>((resolve, reject) => {
            setTimeout(() => {
               Math.random() < 0.5 ? reject("Error") : resolve("Hello World")
            }, 2000)
        })
    }

    root.render(<PromiseComponent f={promiseGiver}/>)
}