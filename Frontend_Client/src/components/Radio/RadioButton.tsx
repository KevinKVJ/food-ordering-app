import { FC, HTMLProps, MouseEventHandler } from 'react';

import Button from '@/components/Buttons/Button';

interface IRadioButtonProps {
    name?: string;
    value: string;
    checked?: boolean;
    disabled?: boolean;
    changeValue?: (val: string) => void;
}

const RadioButton: FC<IRadioButtonProps> = ({ value, changeValue }) => {
    const handleClick: MouseEventHandler = e => {
        // e.nativeEvent.preventDefault();
        changeValue?.(value);
        return e.target;
    };
    return (
        <div className='radio-button-wrapper'>
            <Button onClick={handleClick} />
        </div>
    );
};

export default RadioButton;
