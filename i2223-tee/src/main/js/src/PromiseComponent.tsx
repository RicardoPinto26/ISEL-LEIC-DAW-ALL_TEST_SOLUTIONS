import React, {useEffect, useState} from "react";
import {createRoot} from "react-dom/client";

type PromiseProps = {
    f: () => Promise<string>
}

function PromiseComponent({f}: PromiseProps) {
    const [result, setResult] = useState<string>("");
    const [enable, setEnable] = useState<boolean>(true);

    function callAndSetResult() {
        function callback(promiseResult: any) {
            setResult(promiseResult)
            setEnable(true)
        }

        if (enable) {
            setEnable(false)
            setResult("")
            f().then(callback, callback)
        }
    }

    useEffect(() => {
        callAndSetResult()
    }, [f]);

    return (
        <div>
            <div>
                <button onClick={() => callAndSetResult()} disabled={!enable}>Click me</button>
            </div>
            <div>
                {result || "Loading..."}
            </div>
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