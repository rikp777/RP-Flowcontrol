import Vue from "vue";
import "./plugins/fontawesome";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";


import axios from 'axios'
import VueAxios from 'vue-axios'
Vue.use(VueAxios, axios)


// @ts-ignore
import VueQrcodeReader from "vue-qrcode-reader";
Vue.use(VueQrcodeReader);

import "./assets/tailwind.css";
import VueTailwind from 'vue-tailwind/dist/full'

const settings = {

  // ...Rest of the components
}

Vue.use(VueTailwind, settings)

// import "./assets/tailwind.css";
// import VueTailwind from 'vue-tailwind'
// import {
//   TInput,
//   TTextarea,
//   TSelect,
//   TRadio,
//   TCheckbox,
//   TButton,
//   TInputGroup,
//   TCard,
//   TAlert,
//   TModal,
//   TDropdown,
//   TRichSelect,
//   TPagination,
//   TTag,
//   TRadioGroup,
//   TCheckboxGroup,
//   TTable,
//   TDatepicker,
//   TToggle,
//   TDialog,
// } from 'vue-tailwind/dist/components';
// const settings = {
//   // Use the following syntax
//   // {component-name}: {
//   //   component: {importedComponentObject},
//   //   props: {
//   //     {propToOverride}: {newDefaultValue}
//   //     {propToOverride2}: {newDefaultValue2}
//   //   }
//   // }
//   't-alert': {
//     component: TAlert,
//     props: {
//       fixedClasses: {
//         wrapper: 'relative flex items-center p-4 border-l-4  rounded shadow-sm',
//         body: 'flex-grow',
//         close: 'absolute relative flex items-center justify-center ml-4 flex-shrink-0 w-6 h-6 transition duration-100 ease-in-out rounded  focus:ring-2 focus:ring-blue-500 focus:outline-none focus:ring-opacity-50',
//         closeIcon: 'fill-current h-4 w-4'
//       },
//       classes: {
//         wrapper: 'bg-blue-50 border-blue-500',
//         body: 'text-blue-700',
//         close: 'text-blue-500 hover:bg-blue-200'
//       },
//       variants: {
//         danger: {
//           wrapper: 'bg-red-50 border-red-500',
//           body: 'text-red-700',
//           close: 'text-red-500 hover:bg-red-200'
//         },
//         success: {
//           wrapper: 'bg-green-50 border-green-500',
//           body: 'text-green-700',
//           close: 'text-green-500 hover:bg-green-200'
//         }
//       }
//     }
//   },
//   't-table': {
//     component: TTable,
//     props: {
//       classes: {
//         table: 'min-w-full divide-y divide-gray-100 shadow-sm border-gray-200 border',
//         thead: '',
//         theadTr: '',
//         theadTh: 'px-3 py-2 font-semibold text-left bg-gray-100 border-b',
//         tbody: 'bg-white divide-y divide-gray-100',
//         tr: '',
//         td: 'px-3 py-2 whitespace-no-wrap',
//         tfoot: '',
//         tfootTr: '',
//         tfootTd: ''
//       },
//       variants: {
//         thin: {
//           td: 'p-1 whitespace-no-wrap text-sm',
//           theadTh: 'p-1 font-semibold text-left bg-gray-100 border-b text-sm'
//         }
//       }
//     }
//   },
//   't-radio': {
//     component: TRadio,
//     props: {
//       fixedClasses: 'transition duration-100 ease-in-out shadow-sm focus:border-blue-500 focus:ring-2 focus:ring-blue-500 focus:outline-none focus:ring-opacity-50 focus:ring-offset-0  disabled:opacity-50 disabled:cursor-not-allowed',
//       classes: 'text-blue-500 border-gray-300',
//       variants: {
//         error: 'text-red-500 border-red-300',
//         success: 'text-green-500 border-green-300'
//       }
//     }
//   },
//   't-radio-group': {
//     component: TRadioGroup,
//     props: {
//       classes: {
//         groupWrapper: 'flex flex-col',
//         label: 'ml-2 text-gray-700 uppercase text-sm',
//         input: 'text-blue-500 transition duration-100 ease-in-out border-gray-300 shadow-sm focus:border-blue-500 focus:ring-2 focus:ring-blue-500 focus:outline-none focus:ring-opacity-50 focus:ring-offset-0 disabled:opacity-50 disabled:cursor-not-allowed'
//       },
//       variants: {
//         danger: {
//           groupWrapper: 'flex flex-col',
//           label: 'ml-2 text-red-500 uppercase text-sm',
//           input: 'text-red-500 transition duration-100 ease-in-out border-red-500 shadow-sm focus:border-blue-500 focus:ring-2 focus:ring-blue-500 focus:outline-none focus:ring-opacity-50 focus:ring-offset-0 disabled:opacity-50 disabled:cursor-not-allowed'
//         },
//         buttons: {
//           groupWrapper: 'flex',
//           label: '',
//           labelChecked: '',
//           wrapper: 'mx-1 bg-white border border-gray-300 flex items-center px-4 py-2 rounded shadow-sm cursor-pointer focus:shadow-outline text-sm text-gray-700 hover:text-gray-500 leading-5 uppercase',
//           wrapperChecked: 'mx-1 bg-gray-100 border border-gray-300 flex items-center px-4 py-2 rounded shadow-inner cursor-pointer focus:shadow-outline text-sm text-gray-700 hover:text-gray-500 leading-5 uppercase',
//           inputWrapper: '',
//           inputWrapperChecked: '',
//           input: 'absolute invisible'
//         }
//       }
//     }
//   },
//   't-button': {
//     component: TButton,
//     props: {
//       wrapper: 'table border-collapse text-center bg-white mx-auto shadow-sm',
//       element: 'w-8 h-8 border border-gray-200 table-cell hover:border-blue-100',
//       activeElement: 'w-8 h-8 border border-blue-500 table-cell hover:border-blue-600',
//       disabledElement: 'w-8 h-8 border border-gray-200 table-cell',
//       ellipsisElement: 'w-8 h-8 border border-gray-200 hidden md:table-cell',
//       activeButton: 'bg-blue-500 w-full h-full text-white hover:bg-blue-600 transition duration-100 ease-in-out focus:ring-2 focus:ring-blue-500 focus:outline-none focus:ring-opacity-50',
//       disabledButton: 'opacity-25 w-full h-full cursor-not-allowed transition duration-100 ease-in-out',
//       button: 'hover:bg-blue-100 w-full h-full transition duration-100 ease-in-out focus:ring-2 focus:ring-blue-500 focus:outline-none focus:ring-opacity-50',
//       ellipsis: '',
//     }
//   },
//   't-input': {
//     component: TInput,
//     props: {
//       wrapper: 'relative',
//       input: 'block w-full py-2 pl-3 pr-10 text-black placeholder-gray-400 transition duration-100 ease-in-out bg-white border border-gray-300 rounded shadow-sm bg-none focus:ring-2 focus:ring-blue-500 focus:outline-none focus:ring-opacity-50 disabled:opacity-50 disabled:cursor-not-allowed focus:border-blue-500',
//       arrowWrapper: 'pointer-events-none absolute inset-y-0 right-0 flex items-center px-2',
//       arrow: 'fill-current h-4 w-4'
//     }
//   },
//   't-select': {
//     component: TSelect,
//     props: {
//       wrapper: 'relative',
//       input: 'bg-none block w-full pl-3 pr-10 py-2 transition duration-100 ease-in-out border rounded shadow-sm focus:ring-2 focus:ring-blue-500 focus:outline-none focus:ring-opacity-50 disabled:opacity-50 disabled:cursor-not-allowed',
//       arrowWrapper: 'pointer-events-none absolute inset-y-0 right-0 flex items-center px-2',
//       arrow: 'fill-current h-4 w-4'
//     }
//   },
//   't-rich-select': {
//     component: TRichSelect,
//     props: {
//       fixedClasses: {
//         wrapper: 'relative',
//         buttonWrapper: 'inline-block relative w-full',
//         selectButton: 'w-full flex text-left justify-between items-center px-3 py-2 text-black transition duration-100 ease-in-out border rounded shadow-sm focus:border-blue-500 focus:ring-2 focus:ring-blue-500 focus:outline-none focus:ring-opacity-50 disabled:opacity-50 disabled:cursor-not-allowed',
//         selectButtonLabel: 'block truncate',
//         selectButtonPlaceholder: 'block truncate',
//         selectButtonIcon: 'fill-current flex-shrink-0 ml-1 h-4 w-4',
//         selectButtonClearButton: 'rounded flex flex-shrink-0 items-center justify-center absolute right-0 top-0 m-2 h-6 w-6 transition duration-100 ease-in-out',
//         selectButtonClearIcon: 'fill-current h-3 w-3',
//         dropdown: 'absolute w-full z-10 -mt-1 absolute border-b border-l border-r rounded-b shadow-sm z-10',
//         dropdownFeedback: '',
//         loadingMoreResults: '',
//         optionsList: 'overflow-auto',
//         searchWrapper: 'inline-block w-full',
//         searchBox: 'inline-block w-full',
//         optgroup: '',
//         option: 'cursor-pointer',
//         disabledOption: 'opacity-50 cursor-not-allowed',
//         highlightedOption: 'cursor-pointer',
//         selectedOption: 'cursor-pointer',
//         selectedHighlightedOption: 'cursor-pointer',
//         optionContent: '',
//         optionLabel: 'truncate block',
//         selectedIcon: 'fill-current h-4 w-4',
//         enterClass: '',
//         enterActiveClass: '',
//         enterToClass: '',
//         leaveClass: '',
//         leaveActiveClass: '',
//         leaveToClass: ''
//       },
//       classes: {
//         wrapper: '',
//         buttonWrapper: '',
//         selectButton: 'bg-white border-gray-300',
//         selectButtonLabel: '',
//         selectButtonPlaceholder: 'text-gray-400',
//         selectButtonIcon: 'text-gray-600',
//         selectButtonClearButton: 'hover:bg-blue-100 text-gray-600',
//         selectButtonClearIcon: '',
//         dropdown: 'bg-white border-gray-300',
//         dropdownFeedback: 'pb-2 px-3 text-gray-400 text-sm',
//         loadingMoreResults: 'pb-2 px-3 text-gray-400 text-sm',
//         optionsList: '',
//         searchWrapper: 'p-2 placeholder-gray-400',
//         searchBox: 'px-3 py-2 bg-gray-50 text-sm rounded border focus:outline-none focus:shadow-outline border-gray-300',
//         optgroup: 'text-gray-400 uppercase text-xs py-1 px-2 font-semibold',
//         option: '',
//         disabledOption: '',
//         highlightedOption: 'bg-blue-100',
//         selectedOption: 'font-semibold bg-gray-100 bg-blue-500 font-semibold text-white',
//         selectedHighlightedOption: 'font-semibold bg-gray-100 bg-blue-600 font-semibold text-white',
//         optionContent: 'flex justify-between items-center px-3 py-2',
//         optionLabel: '',
//         selectedIcon: '',
//         enterClass: '',
//         enterActiveClass: 'opacity-0 transition ease-out duration-100',
//         enterToClass: 'opacity-100',
//         leaveClass: 'transition ease-in opacity-100',
//         leaveActiveClass: '',
//         leaveToClass: 'opacity-0 duration-75'
//       },
//       variants: {
//         danger: {
//           selectButton: 'border-red-300 bg-red-50 text-red-900',
//           selectButtonPlaceholder: 'text-red-200',
//           selectButtonIcon: 'text-red-500',
//           selectButtonClearButton: 'hover:bg-red-200 text-red-500',
//           dropdown: 'bg-red-50 border-red-300'
//         },
//         success: {
//           selectButton: 'border-green-300 bg-green-50 text-green-900',
//           selectButtonIcon: 'text-green-500',
//           selectButtonClearButton: 'hover:bg-green-200 text-green-500',
//           dropdown: 'bg-green-50 border-green-300'
//         }
//       }
//     }
//   },
//   't-textarea': {
//     component: TTextarea,
//     props: {
//       classes: 'border-2 block w-full rounded text-gray-800'
//     }
//   },
//
// }
//
// Vue.use(VueTailwind, settings)

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
