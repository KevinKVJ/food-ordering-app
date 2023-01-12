import type { ChangeEventHandler, HTMLInputTypeAttribute } from 'react';

interface inputWithTitleProp {
    className?: string;
    title?: string;
    inputType?: HTMLInputTypeAttribute;
    inputPlaceholder?: string;
    value?: string | number;
    onChange: ChangeEventHandler<HTMLInputElement>;
}

const InputWithTitle = ({
    className,
    title,
    onChange,
    value,
    inputType,
    inputPlaceholder,
}: inputWithTitleProp) => {
    // const actualValue = value || '';
    return (
        <div className={className}>
            <div className='title'>{title}</div>
            <div className='input'>
                {/* {password} */}
                <input
                    type={inputType}
                    placeholder={inputPlaceholder}
                    value={value}
                    onChange={onChange}
                />
            </div>
        </div>
    );
};

export default InputWithTitle;
