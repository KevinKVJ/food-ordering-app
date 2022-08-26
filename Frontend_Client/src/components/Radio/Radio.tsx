import { ChangeEvent, FC } from 'react';

interface IRadio {
    name?: string;
    value: string;
    checked?: boolean;
    disabled?: boolean;
    onChange?: (e: ChangeEvent<HTMLInputElement>) => void;
}

const Radio: FC<IRadio> = ({ name, value, onChange, checked, disabled }) => {
    return (
        <div className='radio-wrapper'>
            {value}
            <input
                type='radio'
                name={name}
                value={value}
                onChange={onChange}
                checked={checked}
                disabled={disabled}
            />
        </div>
    );
};
export default Radio;
