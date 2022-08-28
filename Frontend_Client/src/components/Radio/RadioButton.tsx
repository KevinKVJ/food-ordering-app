import { FC } from 'react';

import Button from '@/components/Buttons/Button';

interface IRadioButtonProps {
    name?: string;
    value: string;
    checked?: boolean;
    disabled?: boolean;
    changeValue?: (val: string) => void;
}

const RadioButton: FC<IRadioButtonProps> = ({ value, changeValue }) => {
    return (
        <div className='radio-button-wrapper'>
            <Button onClick={() => changeValue?.(value)} />
        </div>
    );
};

export default RadioButton;
