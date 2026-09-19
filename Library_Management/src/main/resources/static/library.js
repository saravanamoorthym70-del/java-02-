const api = async (url, options = {}) => {
    const response = await fetch(url, { headers: { 'Content-Type': 'application/json' }, ...options });
    if (!response.ok) {
        const message = await response.text();
        throw new Error(message || `Request failed (${response.status})`);
    }
    return response.status === 204 ? null : response.json();
};

const authorLabel = (author) => author ? `${author.name}${author.email ? ` (${author.email})` : ''}` : 'Unknown author';

async function loadAuthors(select) {
    const authors = await api('/api/authors');
    select.innerHTML = '<option value="">Choose an author</option>';
    authors.forEach((author) => {
        const option = document.createElement('option');
        option.value = author.id;
        option.textContent = authorLabel(author);
        select.append(option);
    });
}

async function loadBooks() {
    const body = document.querySelector('#book-list');
    const count = document.querySelector('#book-count');
    try {
        const books = await api('/api/books');
        count.textContent = `${books.length} ${books.length === 1 ? 'book' : 'books'}`;
        body.innerHTML = books.length ? books.map((book) => `
            <tr>
                <td class="title">${escapeHtml(book.title || 'Untitled')}</td>
                <td>${escapeHtml(book.author?.name || 'Unknown author')}</td>
                <td>${Number(book.price).toFixed(2)}</td>
                <td class="actions">
                    <a class="button secondary" href="/edit-book?id=${book.id}">Edit</a>
                    <button class="danger" type="button" data-delete="${book.id}">Delete</button>
                </td>
            </tr>`).join('') : '<tr><td class="empty" colspan="4">No books have been added yet.</td></tr>';
        body.querySelectorAll('[data-delete]').forEach((button) => button.addEventListener('click', deleteBook));
    } catch (error) {
        body.innerHTML = `<tr><td class="error" colspan="4">${escapeHtml(error.message)}</td></tr>`;
    }
}

async function deleteBook(event) {
    if (!window.confirm('Delete this book?')) return;
    try {
        await api(`/api/books/${event.currentTarget.dataset.delete}`, { method: 'DELETE' });
        await loadBooks();
    } catch (error) {
        window.alert(error.message);
    }
}

function escapeHtml(value) {
    return String(value).replace(/[&<>'"]/g, (character) => ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', "'": '&#39;', '"': '&quot;' }[character]));
}

async function setupForm() {
    const form = document.querySelector('#book-form');
    const author = document.querySelector('#author');
    const id = new URLSearchParams(window.location.search).get('id');
    try {
        await loadAuthors(author);
        if (id) {
            const book = await api(`/api/books/${id}`);
            document.querySelector('#title').value = book.title || '';
            document.querySelector('#price').value = book.price ?? '';
            author.value = book.author?.id || '';
        }
        form.addEventListener('submit', async (event) => {
            event.preventDefault();
            const payload = { title: document.querySelector('#title').value.trim(), price: Number(document.querySelector('#price').value), author: { id: Number(author.value) } };
            await api(id ? `/api/books/${id}` : '/api/books', { method: id ? 'PUT' : 'POST', body: JSON.stringify(payload) });
            window.location.href = '/books';
        });
    } catch (error) {
        document.querySelector('#form-error').textContent = error.message;
    }
}

if (document.body.dataset.page === 'books') loadBooks();
if (document.body.dataset.page === 'form') setupForm();