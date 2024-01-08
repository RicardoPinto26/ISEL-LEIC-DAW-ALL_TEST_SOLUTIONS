import React, {useState} from "react";
import {createRoot} from "react-dom/client";

function useInput(initial: string): [
    currentValue: string,
    changeHandler: (e: React.ChangeEvent<HTMLInputElement>) => void
] {
    const [value, setValue] = useState<string>(initial)

    function changeHandler(e: React.ChangeEvent<HTMLInputElement>) {
        setValue(e.target.value)
    }

    return [value, changeHandler]
}

function Example() {
    const [value, handler] = useInput("")
    return (
        <input type="text" value={value} onChange={handler}/>
    );
}

export function demo() {
    const root = createRoot(document.getElementById("container"))
    root.render(<Example/>)
}