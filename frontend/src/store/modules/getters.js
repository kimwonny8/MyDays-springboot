// const storedTodoItems = (state) => {
//     return state.todoItems;
// };
// const storedName = (state) => {
//     return state.userName;
// };
// const storedTodoItemsCount = (state, getters) => {
//     return getters.storedTodoItems.length;
// }

// export { storedTodoItems, storedName, storedTodoItemsCount };


const storedId = (state) => {
    return state.storedId;
};

const storedPw = (state) => {
    return state.storedPw;
};

const storedName = (state) => {
    return state.storedName;
};

const storedBirth = (state) => {
    return state.storedBirth;
};

export { storedId, storedPw, storedName, storedBirth };