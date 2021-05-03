// Vue Tailwind config
import {
    TInput,
    TTextarea,
    TSelect,
    TRadio,
    TRadioGroup,
    TCheckbox,
    TButton,
    TInputGroup,
    TDropdown,
    TRichSelect,
    TToggle,
    TCheckboxGroup
} from 'vue-tailwind/dist/components';

const settings = {
    't-input': {
        component: TInput,
        props: {
            fixedClasses: 'block w-full px-3 py-1.5 transition duration-100 ease-in-out border rounded focus:ring-2 focus:outline-none focus:ring-opacity-50 disabled:opacity-50 disabled:cursor-not-allowed',
            classes: 'text-gray-900 placeholder-gray-400 bg-white border-gray-300 focus:border-purple-500',
            variants: {
                primary: 'text-gray-900 placeholder-gray-400 bg-white border-gray-300 focus:border-primary focus:ring-primary',
                light: 'text-gray-900 placeholder-gray-400 bg-gray-100 border-gray-200 focus:border-primary focus:ring-primary',
                danger: 'text-red-900 placeholder-red-400 bg-red-50 border-red-300 focus:border-primary focus:ring-red-500',
                success: 'text-green-900 placeholder-green-400 bg-green-50 border-green-300 focus:border-primary focus:ring-green-500',
                warning: 'text-yellow-900 placeholder-yellow-400 bg-red-50 border-yellow-300 focus:border-primary focus:ring-yellow-500',
                black: 'text-gray-200 placeholder-gray-300 bg-gray-800 border-gray-900 focus:border-primary focus:ring-white'
            }
        }
    },
    't-textarea': {
        component: TTextarea,
        props: {
            fixedClasses: 'block w-full px-3 py-1.5 transition duration-100 ease-in-out border rounded focus:ring-2 focus:ring-purple-500 focus:outline-none focus:ring-opacity-50 disabled:opacity-50 disabled:cursor-not-allowed',
            classes: 'text-gray-900 placeholder-gray-400 bg-white border-gray-300 focus:border-purple-500 ',
            variants: {
                danger: 'border-red-300 bg-red-50 placeholder-red-200 text-red-900',
                success: 'border-green-300 bg-green-50 placeholder-gray-400 text-green-900'
            }
        }
    },
    't-select': {
        component: TSelect,
        props: {
            fixedClasses: 'block w-full pl-3 pr-10 py-1.5 transition duration-100 ease-in-out border rounded focus:ring-2 focus:ring-purple-500 focus:outline-none focus:ring-opacity-50 disabled:opacity-50 disabled:cursor-not-allowed',
            classes: 'text-gray-900 placeholder-gray-400 bg-white border-gray-300 focus:border-purple-500 ',
            variants: {
                white: 'border-gray-200 bg-white placeholder-gray-500 text-gray-900',
                danger: 'border-red-300 bg-red-50 placeholder-red-200 text-red-900',
                success: 'border-green-300 bg-green-50 placeholder-gray-400 text-green-900'
            }
        }
    },
    't-radio': {
        component: TRadio,
        props: {
            fixedClasses: 'transition duration-100 ease-in-out shadow-sm focus:border-purple-500 focus:ring-2 focus:ring-purple-500 focus:outline-none focus:ring-opacity-50 focus:ring-offset-0  disabled:opacity-50 disabled:cursor-not-allowed',
            classes: 'text-purple-500 border-gray-300 ',
            variants: {
                error: 'text-red-500 border-red-300 focus:border-red-500 focus:ring-red-500',
                success: 'text-green-500 border-gray-300 focus:border-green-500 focus:ring-green-500',
                warning: 'text-yellow-500 border-yellow-300 focus:border-yellow-500 focus:ring-yellow-500'
            }
        }
    },
    't-radio-group': {
        component: TRadioGroup,
        props: {
            classes: {
                groupWrapper: 'flex flex-col',
                label: 'ml-2 text-gray-700 uppercase text-sm',
                input: 'text-blue-500 transition duration-100 ease-in-out border-gray-300 shadow-sm focus:border-blue-500 focus:ring-2 focus:ring-blue-500 focus:outline-none focus:ring-opacity-50 focus:ring-offset-0 disabled:opacity-50 disabled:cursor-not-allowed'

            },
            variants: {
                danger: {
                    groupWrapper: 'flex flex-col',
                    label: 'ml-2 text-red-500 uppercase text-sm',
                    input: 'text-red-500 transition duration-100 ease-in-out border-red-500 shadow-sm focus:border-blue-500 focus:ring-2 focus:ring-blue-500 focus:outline-none focus:ring-opacity-50 focus:ring-offset-0 disabled:opacity-50 disabled:cursor-not-allowed'
                },
                buttons: {
                    groupWrapper: 'flex',
                    label: '',
                    labelChecked: '',
                    wrapper: 'mx-1 bg-white border border-gray-300 flex items-center px-4 py-2 rounded shadow-sm cursor-pointer focus:shadow-outline text-sm text-gray-700 hover:text-gray-500 leading-5 uppercase',
                    wrapperChecked: 'mx-1 bg-gray-100 border border-gray-300 flex items-center px-4 py-2 rounded shadow-inner cursor-pointer focus:shadow-outline text-sm text-gray-700 hover:text-gray-500 leading-5 uppercase',
                    inputWrapper: '',
                    inputWrapperChecked: '',
                    input: 'absolute invisible'
                }
            }
        }
    },
    't-checkbox': {
        component: TCheckbox,
        props: {
            fixedClasses: 'transition duration-100 ease-in-out rounded focus:border-purple-500 focus:ring-2 focus:ring-purple-500 focus:outline-none focus:ring-opacity-50 focus:ring-offset-0 disabled:opacity-50 disabled:cursor-not-allowed',
            classes: 'text-purple-500 border-gray-300 ',
            variants: {
                error: 'text-red-500 border-red-300',
                success: 'text-green-500 border-green-300'
            }
        }
    },
    't-button': {
        component: TButton,
        props: {
            fixedClasses: 'block px-4 py-1.5 h-9 rounded transition text-sm font-bold duration-100 ease-in-out focus:border-purple-500 focus:ring-2 focus:ring-purple-500 focus:outline-none focus:ring-opacity-50 disabled:opacity-50 disabled:cursor-not-allowed',
            classes: 'text-white bg-purple-500 border border-transparent hover:bg-purple-600',
            variants: {
                primary: 'text-white bg-primary border border-transparent hover:bg-opacity-70',
                secondary: 'text-white bg-secondary border border-transparent hover:bg-opacity-70',
                error: 'text-white bg-red-500 border border-transparent hover:bg-red-600',
                success: 'text-white bg-green-500 border border-transparent hover:bg-green-600',
                white: 'text-gray-700 bg-white border border-transparent hover:bg-gray-100',
                gray100: 'text-gray-600 bg-gray-100 border border-transparent hover:bg-gray-200',
                gray200: 'text-gray-600 bg-gray-200 border border-transparent hover:bg-gray-300',
                gray300: 'text-gray-600 bg-gray-300 border border-transparent hover:bg-gray-400',
                gray400: 'text-white bg-gray-400 border border-transparent hover:bg-gray-500',
                gray500: 'text-white bg-gray-500 border border-transparent hover:bg-gray-600',
                gray600: 'text-white bg-gray-600 border border-transparent hover:bg-gray-700',
                outlinePurple500: 'text-purple-500 bg-white border border-purple-500 hover:bg-purple-500 hover:text-white',
                purple500: 'text-white bg-purple-500 border border-transparent hover:bg-purple-600',
                outlineRed500: 'text-red-500 bg-white border border-red-500 hover:bg-red-500 hover:text-white',
                red500: 'text-white bg-red-500 border border-transparent hover:bg-red-600',
                black30: 'text-white bg-black border border-transparent bg-opacity-30 hover:bg-opacity-50',
                link: 'text-purple-500 hover:text-purple-600',
                linkGray400: 'text-gray-400 hover:text-gray-500',
                linkGray500: 'text-gray-500 hover:text-gray-600',
                linkGray600: 'text-gray-600 hover:text-gray-700'
            }
        }
    },
    't-input-group': {
        component: TInputGroup,
        props: {
            fixedClasses: {
                wrapper: '',
                label: 'block text-sm font-medium mb-1',
                body: '',
                feedback: 'text-sm text-sm',
                description: 'text-gray-400 text-sm'
            },
            classes: {
                wrapper: '',
                label: '',
                body: '',
                feedback: 'text-gray-400 dark:text-gray-200',
                description: 'text-gray-400 dark:text-gray-200'
            },
            variants: {
                danger: {
                    label: 'text-red-500',
                    feedback: 'text-red-500'
                },
                success: {
                    label: 'text-green-500',
                    feedback: 'text-green-500'
                }
            }
        }
    },
    't-rich-select': {
        component: TRichSelect,
        props: {
            fixedClasses: {
                wrapper: 'relative',
                buttonWrapper: 'inline-block relative w-full',
                selectButton: 'w-full flex text-left justify-between items-center px-4 py-1.5 text-gray-900 transition duration-100 ease-in-out border rounded focus:border-purple-500 focus:ring-2 focus:ring-purple-500 focus:outline-none focus:ring-opacity-50 disabled:opacity-50 disabled:cursor-not-allowed',
                selectButtonLabel: 'block truncate',
                selectButtonPlaceholder: 'block truncate',
                selectButtonIcon: 'fill-current flex-shrink-0 ml-1 h-4 w-4',
                selectButtonClearButton: 'rounded flex flex-shrink-0 items-center justify-center absolute right-0 top-0 m-2 w-5 h-5 transition duration-100 ease-in-out',
                selectButtonClearIcon: 'fill-current h-3 w-3',
                dropdown: 'absolute w-full z-10 -mt-1 border-b border-l border-r rounded-b-sm shadow-sm z-10',
                dropdownFeedback: '',
                loadingMoreResults: '',
                optionsList: 'overflow-auto',
                searchWrapper: 'inline-block w-full',
                searchBox: 'inline-block w-full',
                optgroup: '',
                option: 'cursor-pointer',
                disabledOption: 'opacity-50 cursor-not-allowed',
                highlightedOption: 'cursor-pointer',
                selectedOption: 'cursor-pointer',
                selectedHighlightedOption: 'cursor-pointer',
                optionContent: '',
                optionLabel: 'truncate block',
                selectedIcon: 'fill-current h-4 w-4',
                enterClass: '',
                enterActiveClass: '',
                enterToClass: '',
                leaveClass: '',
                leaveActiveClass: '',
                leaveToClass: ''
            },
            classes: {
                wrapper: '',
                buttonWrapper: '',
                selectButton: 'bg-light border-gray-300',
                selectButtonLabel: '',
                selectButtonPlaceholder: 'text-gray-400',
                selectButtonIcon: 'text-gray-600',
                selectButtonClearButton: 'hover:bg-purple-100 text-gray-600',
                selectButtonClearIcon: '',
                dropdown: 'bg-white border-gray-300',
                dropdownFeedback: 'pb-2 px-3 text-gray-400 text-sm',
                loadingMoreResults: 'pb-2 px-3 text-gray-400 text-sm',
                optionsList: '',
                searchWrapper: 'p-2 placeholder-gray-400',
                searchBox: 'px-3 py-2 bg-gray-50 text-sm rounded border focus:outline-none focus:shadow-outline border-gray-300',
                optgroup: 'text-gray-400 uppercase text-xs py-1 px-2 font-semibold',
                option: '',
                disabledOption: '',
                highlightedOption: 'bg-gray-100',
                selectedOption: 'font-bold bg-gray-100 bg-purple-500 text-sm text-white',
                selectedHighlightedOption: 'bg-gray-100 bg-purple-600 font-bold text-white',
                optionContent: 'flex justify-between items-center px-3 py-2',
                optionLabel: '',
                selectedIcon: '',
                enterClass: '',
                enterActiveClass: 'opacity-0 transition ease-out duration-100',
                enterToClass: 'opacity-100',
                leaveClass: 'transition ease-in opacity-100',
                leaveActiveClass: '',
                leaveToClass: 'opacity-0 duration-75'
            },
            variants: {
                error: {
                    selectButton: 'border-red-300 bg-red-50 text-red-900',
                    selectButtonPlaceholder: 'text-red-200',
                    selectButtonIcon: 'text-red-500',
                    selectButtonClearButton: 'hover:bg-red-200 text-red-500',
                    dropdown: 'bg-red-50 border-red-300'
                },
            },
        }
    },
    't-checkbox-group': {
        component: TCheckboxGroup,
        props: {
            classes: {
                groupWrapper: 'flex flex-col space-y-1',
                label: 'ml-2 text-gray-700 text-sm',
                input: 'transition duration-100 ease-in-out rounded shadow-sm focus:border-purple-500 focus:ring-2 focus:ring-purple-500 focus:outline-none focus:ring-opacity-50 focus:ring-offset-0  disabled:opacity-50 disabled:cursor-not-allowed text-purple-500 border-gray-300'
            },
            variants: {
                danger: {
                    groupWrapper: 'flex flex-col space-y-1',
                    label: 'ml-2 text-red-500 text-sm',
                    input: 'transition duration-100 ease-in-out rounded shadow-sm focus:border-purple-500 focus:ring-2 focus:ring-purple-500 focus:outline-none focus:ring-opacity-50 focus:ring-offset-0  disabled:opacity-50 disabled:cursor-not-allowed text-red-500 border-red-500'
                },
                buttons: {
                    groupWrapper: 'flex',
                    label: '',
                    labelChecked: '',
                    wrapper: 'mx-1 bg-white border border-gray-300 flex items-center px-4 py-2 rounded shadow-sm cursor-pointer focus:shadow-outline text-sm text-gray-700 hover:text-gray-500 leading-5 uppercase',
                    wrapperChecked: 'mx-1 bg-gray-100 border border-gray-300 flex items-center px-4 py-2 rounded shadow-inner cursor-pointer focus:shadow-outline text-sm text-gray-700 hover:text-gray-500 leading-5 uppercase',
                    inputWrapper: '',
                    inputWrapperChecked: '',
                    input: 'absolute invisible'
                }
            }
        }
    },
    't-toggle': {
        component: TToggle,
        props: {
            fixedClasses: {
                wrapper: 'relative inline-flex flex-shrink-0 cursor-pointer transition-colors ease-in-out duration-200',
                wrapperChecked: 'relative inline-flex flex-shrink-0 cursor-pointer transition-colors ease-in-out duration-200  border-2 border-transparent focus:ring-2 focus:ring-purple-500 focus:outline-none focus:ring-opacity-50',
                wrapperDisabled: 'relative inline-flex flex-shrink-0 cursor-pointer transition-colors ease-in-out duration-200 opacity-50 cursor-not-allowed',
                wrapperCheckedDisabled: 'relative inline-flex flex-shrink-0 cursor-pointer transition-colors ease-in-out duration-200 opacity-50 cursor-not-allowed border-2 border-transparent focus:ring-2 focus:ring-purple-500 focus:outline-none focus:ring-opacity-50',
                button: 'inline-block absolute transform translate-x-0 transition ease-in-out duration-200',
                buttonChecked: 'inline-block absolute transform translate-x-full transition ease-in-out duration-200',
                checkedPlaceholder: 'inline-block',
                uncheckedPlaceholder: 'inline-block'
            },
            classes: {
                wrapper: 'bg-gray-200 rounded-full border-2 border-transparent focus:ring-2 focus:ring-purple-500 focus:outline-none focus:ring-opacity-50',
                wrapperChecked: 'bg-purple-500 rounded-full',
                wrapperDisabled: 'bg-gray-200 rounded-full border-2 border-transparent focus:ring-2 focus:ring-purple-500 focus:outline-none focus:ring-opacity-50',
                wrapperCheckedDisabled: 'bg-purple-500',
                button: 'h-4 w-4 rounded-full bg-white shadow flex items-center justify-center text-gray-400 text-xs',
                buttonChecked: 'h-4 w-4 rounded-full bg-white shadow flex items-center justify-center text-purple-500 text-xs',
                checkedPlaceholder: 'rounded-full w-4 h-4 flex items-center justify-center text-gray-400 text-xs',
                uncheckedPlaceholder: 'rounded-full w-4 h-4 flex items-center justify-center text-gray-400 text-xs'
            },
            variants: {
                danger: {
                    wrapperChecked: 'bg-red-500 rounded-full',
                    wrapperCheckedDisabled: 'bg-red-500 rounded-full'
                },
                success: {
                    wrapperChecked: 'bg-green-500 rounded-full',
                    wrapperCheckedDisabled: 'bg-green-500 rounded-full'
                },
                box: {
                    wrapper: 'bg-gray-100 rounded border-2 border-transparent focus:ring-2 focus:ring-purple-500 focus:outline-none focus:ring-opacity-50',
                    wrapperChecked: 'bg-purple-500 rounded',
                    wrapperCheckedDisabled: 'bg-purple-500 rounded',
                    button: 'w-4 h-4 rounded bg-white shadow flex items-center justify-center text-gray-400 text-xs',
                    buttonChecked: 'w-4 h-4 rounded  bg-white shadow flex items-center justify-center text-purple-500 text-xs',
                    checkedPlaceholder: 'rounded h-5 w-5 flex items-center justify-center text-gray-400 text-xs',
                    uncheckedPlaceholder: 'rounded h-5 w-5 flex items-center justify-center text-gray-400 text-xs'
                }
            }
        }
    },
    't-dropdown': {
        component: TDropdown,
        props: {
            fixedClasses: {
                button: 'flex items-center text-white block px-4 py-2 transition duration-100 ease-in-out border border-transparent rounded focus:border-purple-500 focus:ring-2 focus:ring-purple-500 focus:outline-none focus:ring-opacity-50 disabled:opacity-50 disabled:cursor-not-allowed',
                wrapper: 'inline-flex flex-col',
                dropdownWrapper: 'relative z-50',
                dropdown: 'absolute rounded shadow-lg border mt-1 overflow-hidden',
                enterClass: '',
                enterActiveClass: 'transition ease-out duration-100 transform opacity-0 scale-95',
                enterToClass: 'transform opacity-100 scale-100',
                leaveClass: 'transition ease-in transform opacity-100 scale-100',
                leaveActiveClass: '',
                leaveToClass: 'transform opacity-0 scale-95 duration-75'
            },
            classes: {
                button: 'bg-purple-500 hover:bg-purple-600',
                dropdown: 'w-56 origin-top-left left-0 bg-white py-2'
            },
            variants: {
                danger: {
                    button: 'bg-red-500 hover:bg-red-600',
                    dropdown: 'bg-red-50'
                }
            }
        }
    }
};

export default settings;