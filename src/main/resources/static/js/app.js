document.querySelectorAll('tbody tr').forEach((row) => {
  const fields = row.querySelectorAll('.mark');
  const preview = row.querySelector('.preview');

  const clampMark = (value) => {
    if (!Number.isFinite(value)) {
      return null;
    }
    return Math.min(100, Math.max(0, value));
  };

  const refresh = () => {
    const mse = clampMark(parseFloat(fields[0].value));
    const ese = clampMark(parseFloat(fields[1].value));

    if (mse === null || ese === null) {
      preview.textContent = '—';
      return;
    }

    preview.textContent = (mse * 0.3 + ese * 0.7).toFixed(2);
  };

  fields.forEach((field) => {
    field.addEventListener('input', () => {
      const parsed = parseFloat(field.value);
      if (Number.isFinite(parsed)) {
        field.value = Math.min(100, Math.max(0, parsed));
      }
      refresh();
    });

    field.addEventListener('blur', () => {
      const parsed = parseFloat(field.value);
      if (Number.isFinite(parsed)) {
        field.value = Math.min(100, Math.max(0, parsed));
      }
      refresh();
    });
  });

  refresh();
});
