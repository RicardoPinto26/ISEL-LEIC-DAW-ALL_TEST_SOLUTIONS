import React, {useState} from 'react';
import {createRoot} from "react-dom/client";

function useCounter(initial: number): [observed: number, inc: () => void, dec: () => void] {
    const [value, setValue] = useState(initial)

    const increment = () => {
        setValue(val => val + 1)
    }
    const decrement = () => {
        setValue(val => val - 1)
    }

    return [value, increment, decrement]
}

export function CounterComponent() {
    const [count, increment, decrement] = useCounter(0)

    return (
        <div>
            <p>Counter Value: {count}</p>
            <button onClick={increment}>Increment</button>
            <button onClick={decrement}>Decrement</button>
        </div>
    )
}

export function demo() {
    const root = createRoot(document.getElementById("container"))
    root.render(<CounterComponent/>)
}