import React, {useEffect, useState} from 'react';
import {createRoot} from 'react-dom/client'

function CountingComponent() {
    const [count, setCount] = useState(50);
    const [initial, setInitial] = useState(50);
    const [enable, setEnable] = useState(false);

    useEffect(() => {
        const intervalID = setInterval(() => {
            setCount(count => {
                setEnable((curr) => curr && count > 1);
                return count + (enable && count > 0 ? -1 : 0)
            });
        }, 1000);
        return () => clearInterval(intervalID);
    }, [initial, enable]);

    const text = enable ? "Stop" : "Start";

    return <div>
        <button onClick={() => setEnable(val => !val)} disabled={count < 1}>{text}</button>
        <button onClick={() => {
            setCount(initial)
            setEnable(true)
        }}>Reset
        </button>
        <input type="number" value={initial} onChange={event => setInitial(parseInt(event.target.value))}/>
        <p>{count}</p>
    </div>
}

export function demo() {
    const root = createRoot(document.getElementById("container"))
    root.render(<CountingComponent/>)
}