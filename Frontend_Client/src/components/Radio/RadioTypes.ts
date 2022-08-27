import { ChangeEvent } from 'react';

export interface IRadio {
    name?: string;
    value: string;
    checked?: boolean;
    disabled?: boolean;
    onChange?: (e: ChangeEvent<HTMLInputElement>) => void;
}
