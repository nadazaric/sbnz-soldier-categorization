import React, { useState, useEffect } from 'react';
import style from '../styles/DropdownMenu.module.css'
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import { getTranslation } from '@/locales/TranslationHelper';

export function DropdownMenuNavbarOption({ options, selectedDefault, onSelect, alwaysOnText = null }) {
  const [anchorEl, setAnchorEl] = useState(null)
  const [selectedOption, setSelectedOption] = useState(null)

  const t = getTranslation()

  useEffect(() => {
    if (!selectedDefault && options && options.length > 0) {
      setSelectedOption(options[0])
    } else if (options && options.length > 0) {
      const defaultOption = options.find(option => option.value === selectedDefault)
      setSelectedOption(defaultOption)
    }
  }, [options, selectedDefault])

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget)
  }

  const handleClose = (option) => {
    setSelectedOption(option)
    setAnchorEl(null)
    if (onSelect) onSelect(option)
  }

  if (!options || options.length === 0) {
    return <div>{t.no_data}</div>
  }

  return (
    <div>

        <label 
            disableRipple 
            className={style.dropdownButton} 
            onClick={handleClick}>
                <div>
                    {selectedOption ? selectedOption.text : ''}
                </div>
        </label>

        <Menu
            anchorEl={anchorEl}
            open={Boolean(anchorEl)}
            onClose={() => setAnchorEl(null)}
            PaperProps={{
            style: {
                maxHeight: 200,
                maxWidth: 600
            },
            }}
        >
            <div>
                {options.map((option) => (
                    <MenuItem 
                        key={option.value} 
                        onClick={() => handleClose(option)} 
                        style={{ fontSize: '14px', whiteSpace: 'normal' }}>
                        {option.text}
                    </MenuItem>
                ))}
            </div>
        </Menu>
    </div>
  )
}